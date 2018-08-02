import sys
import wget
import requests
import argparse
import re
from BeautifulSoup import BeautifulSoup
from xml.dom.minidom import parse


class ParseSite(object):
    def __init__(self):
        arguments = self.create_args(sys.argv[1:])
        self.__hostname = None
        self.__site = arguments.site
        self.__sitemap_file = "sitemap.xml"
        self.__parse_url = arguments.search_url
        self.__parse_content = arguments.search_page
        self.__keyword = arguments.keyword
        self.__is_auth_required = arguments.auth
        if self.__is_auth_required:
            self.__auth_endpoint = arguments.auth_endpoint
            self.__username = arguments.auth_username
            self.__password = arguments.auth_password
            self.__login_info = {'email': self.__username, 'password': self.__password}
        self.__site_map_urls = []
        self.__urls = []
        self.__session = requests.session()

        self.parse_site()

    def parse_site(self):
        self.get_host_name()
        # Parses the site provided
        if self.__is_auth_required != "True" :
            self.get_site_map_file()
            self.get_site_map_urls()
        else:
            self.login_to_endpoint()
        self.get_all_urls()
        self.search_keyword()

    def login_to_endpoint(self):
        url = self.__site + self.__auth_endpoint
        login_response = self.__session.get(url)

        matchme = '<meta name="_csrf" content="(.*)"/>'
        csrf = re.search(matchme, str(login_response.text))
        csrf_token = csrf.group(1)

        # Set the headers of the request
        headers = {
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) \
                                Chrome/55.0.2883.87 Safari/537.36',
            'Origin': 'https://' + self.__hostname,
            'Referer': 'https://' + self.__hostname + "/" + self.__auth_endpoint,
            'Host': self.__hostname,
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            'X-Requested-With': 'XMLHttpRequest',
            'X-CSRF-TOKEN': csrf_token,
        }
        self.__session.headers = headers
        dashboard_response = self.__session.post(self.__site + "/dashBoardUrl", data=self.__login_info, allow_redirects=False)

        if dashboard_response.status_code == 302:
            myaccount_response = self.__session.get(self.__site + "/myaccount", verify=False)
            page = BeautifulSoup(myaccount_response.content)
            links = page.findAll('a')

            for tag in links:
                link = tag.get('href', None)
                if link is not None:
                    self.__site_map_urls.append(link)

    def get_host_name(self):
        site_parts = self.__site.split("/")
        self.__hostname = site_parts[2]

    def get_site_map_file(self):
        # Download the site map file
        url = self.__site + "/" + self.__sitemap_file
        wget.download(url, out=self.__sitemap_file)

    def get_site_map_urls(self):
        dom = parse(self.__sitemap_file)
        urls = dom.getElementsByTagName("url")
        for url in urls[1:]:
            link = url.getElementsByTagName("loc")[0]
            url_string = link.firstChild.data

            link_components = url_string.split("/")
            link_components[2] = self.__hostname
            url_string = '/'.join(link_components)
            self.__site_map_urls.append(url_string)

    def get_all_urls(self):
        print("Parsing QBR to retrieve all URLs - "),
        final_links = []
        for main_link in self.__site_map_urls:
            if main_link != '#' and main_link.startswith('/'):
                final_links.append(main_link)
                # Open a session object
                resp = self.__session.get(self.__site + main_link)
 
                page = BeautifulSoup(resp.content)
                links = page.findAll('a')
 
                for tag in links:
                    sub_link = None
                    sub_link = tag.get('href', None)
                    if sub_link is not None and \
                            not sub_link.startswith('http') and \
                            sub_link != "/" and \
                            sub_link != '#' and \
                            sub_link.find("http") != -1:
                        final_links.append(main_link + sub_link)
                    else:
                        final_links.append(sub_link)
        self.__urls = list(set(final_links))
        print(" Done!")
#         fh = open("links.txt", "w")
#         for link in final_links:
#             fh.write(link + "\n")
#         fh.close()
    def search_keyword(self):
        import re
        regex_string = ".+(" + self.__keyword + "\s\d\d\s\d\d)"
        pattern = re.compile(regex_string)
        for page in self.__urls:
            if page and page.startswith('/'):
                link = self.__site + page
                if link and link.find(self.__site) != -1:
                    if self.__parse_url == "true":
                        link = str(link)
                        if link == self.__keyword:
                            print ("Link that contains keyword'" + self.__keyword + "' in its URL - " + link)
                    if self.__parse_content == "true":
                        resp = self.__session.get(link)
                        pageContent = BeautifulSoup(resp.content)
                        
                        for script in pageContent(["script", "style"]):
                            script.extract()
    
                        body = pageContent.getText()
                        match = re.findall(pattern, body)
                        if match:
                            for item in match:
                                if (item.find("Oops") != -1) or (item.find("Unfortunately") != -1):
                                    next
                                else:
                                    print("Link '" + link + "' contains a contact number - '" + item + "'")
                                    #print(item)                      
                        else:
                            print(link + " ---> doesn't not contain string " + self.__keyword)

    def create_args(self, args=None):
        parser = argparse.ArgumentParser(description="A tool to parse website and search for strings in page and URLs")
        parser.add_argument("--site", help="URL of website to be parsed", required=True)
        parser.add_argument("--search-url", help="Set to 'true' to search if URL contains a specific string", required=True, default=True)
        parser.add_argument("--search-page", help="Set to 'true' to search if a page contains a specific string", default=False)
        parser.add_argument("--keyword", help="Provide a string to search", required=True)
        parser.add_argument("--display-match", help="Set to true to display the line that contains the keyword", default=False)
        parser.add_argument("--extact-match", help="Set to true for a exact match of the keyword, not just contains", default=False)
        parser.add_argument("--auth", help="A boolean value to specify if login is required for site", default=False)
        parser.add_argument("--auth-endpoint", help="Endpoint for which authentication is required", default="/login")
        parser.add_argument("--auth-username", help="Username if authentication is required", default=None)
        parser.add_argument("--auth-password", help="Password if authentication is required", default=None)

        return parser.parse_args(args)

if __name__ == '__main__':
    ParseSite()