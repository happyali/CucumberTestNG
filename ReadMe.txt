https://www.youtube.com/watch?v=wmsuWw5Qm3c&list=PLjLEaytWLnzRPEuVyyl5WK5TMfIkdtDTC  ( Dmitry: Selenium-Java-testNG framework)

POM file(maven-surefire-plugin) --->TestNGFile ---> Test/Class?method (if any defined in xml)
---> FeatureRunnerFile ---> GlueFile ---> TestStepsDef ---> Pages ---> BaseTest & PageObjects

BaseTest 		---> All basic reusable steps required in the frameworks
TestStepsDef 	---> Any test related to the glue defined
Pages 			---> All page related activities and actions
PageObjects 	---> All objects from the particular page.





Thumb Rule:
# All Cucumber pom dependencies should have same versions.
# Use Ctrl + / to format code
# Ctrl + Shift + O manage all imports
# Use Ctrl + / to comment and Ctrl + \ to uncomment.
# Pressing Ctrl + Shift + L , will list all the shortcuts.
# $x("")

**************************************************************************************************
@Test(priority=#) will decide which test need to run in priority
@Test(description="test case or functionality" --- this will appears in console and testNG reports

**************************************************************************************************
# Python Script Help:

1. Navigate to Python folder and you should have pip
2. Set PATH the System variable of environment variable
3. Install all below parameter required to run Python scripts as
	(/C/Python27/Scripts/pip.exe install wget)
	install wget
	install requests
	install argparse
	install BeautifulSoup
	install minidom
4 For help --   C/Python27/python.exe Filename.py --h (here filename : ParseQBR.py)
5.To run the python scripts:
   C/Python27/python.exe ParseQBR.py --site https://www-06.sit.qantasbusinessrewards.com --search-url false --search-page true --keyword account --auth-username alitest1@rest.com --auth-password Test@12345 --auth true

**************************************************************************************************
npm:
locally: If want to use in own module as require 				npm install lodash
globally: If want to use as CLI tool							npm install -g grunt-cli 
__________________________________________________________________________________________________
To install:
"dependencies": These packages are required by your application in production.
"devDependencies": These packages are only needed for development and testing.
To add an entry to your package.json's dependencies:
		npm install <package_name> --save
To add an entry to your package.json's devDependencies:
		npm install <package_name> --save-dev
__________________________________________________________________________________________________
To update: in package.json
npm ls
npm outdated -- can see wanted version if any
npm update
npm update -g <package>
__________________________________________________________________________________________________
To uninstall / remove a package from your node_modules directory, use:
	npm uninstall <package>:
	npm uninstall lodash
	npm unistall -g <package>

	To remove it from the dependencies in package.json, you will need to use the save flag:	
	npm uninstall --save lodash
	Note: if you installed the package as a "devDependency" (i.e. with --save-dev) then --save won't remove it from package.json. You have to use --save-dev to uninstall it.

If package is removed from package.json but it present in node_modules folder then "npm ls" wil say extraneous
So you npm prune
This will remove all unwanted packages
__________________________________________________________________________________________________





# How to run this project from command line:
	1. Navigate to project directory 
	2. Run mvn clean compile test exec:java@generateCucumberReport -P${testType} -Durl=\"${url}\" -Dbrowser=${browser} -DbranchName=${branch} -DbuildEnv=${env} -DbuildNo=1.0.0.1 -Dlog4j.configurationFile=log4j2.xml"
	e.g.
	mvn clean compile test exec:java@generateCucumberReport -Psmoke -Durl="https://qantas-business-rewards-02.tst.qantasloyalty.net" -Dbrowser=chrome -DbranchName=master -DbuildEnv=test -DbuildNo=1.0.0.1 -Dlog4j.configurationFile=log4j2.xml

	
# To Learn more about Surefire:
	http://maven.apache.org/surefire/maven-surefire-plugin/
	
	
# Rules:
1. You may want to run two providers, e.g. surefire-junit47 and surefire-testng, and avoid running JUnit tests within surefire-testng provider by setting property junit=false.
2. TestNG allows you to run your tests in parallel, including JUnit tests. To do this, you must set the parallel parameter, and may change the threadCount parameter if the default of 5 is not sufficient.
3. You can also skip the tests via the command line by executing the following command:  mvn install -DskipTests
   If you absolutely must, you can also use the maven.test.skip property to skip compiling the tests. maven.test.skip is honored by Surefire, Failsafe and the Compiler Plugin.
   mvn install -Dmaven.test.skip=true 
   
   
# configuration that can be specified in the POM are following −
	project dependencies, plugins, goals, build profiles,	project version,	developers,	mailing list
	
# Maven Build Lifecycle consists of the following sequence of phases.
	prepare-resource, build, validate, compile, Test, package, install, Deploy
	
# Maven has the following three standard lifecycles: clean (pre-clean, clean, post-clean), default and site (pre-site, site, post-site, site-deploy)

# Maven search sequence: local repo (on test machine), Central repo (Maven Community, Error message and Remote repo (over internet if not found in first)

# If dependency is not available in any of remote repositories and central repository then it is define in as External Dependency:
	External dependencies (library jar location) can be configured in pom.xml in same way as other dependencies.
	Specify groupId same as the name of the library.
	Specify artifactId same as the name of the library.
	Specify scope as system.
	Specify system path relative to the project location.
	
# Profile: A Build profile is a set of configuration values, which can be used to set or override default values of Maven build. Profiles modify the POM at build time, and are used to give parameters 
	different target environments (for example, the path of the database server in the development, testing, and production environments).
		
# mvn clean package:
	We give maven two goals, first to clean the target directory (clean) and then package the project build output as jar (package).
	Packaged jar is available in consumerBanking\target folder as consumerBanking-1.0-SNAPSHOT.jar.
	Test reports are available in consumerBanking\target\surefire-reports folder.
	Maven compiles the source code file(s) and then tests the source code file(s).
	Then Maven runs the test cases.
	Finally, Maven creates the package.
__________________________________________________________________________________________________


__________________________________________________________________________________________________

__________________________________________________________________________________________________
	
	
__________________________________________________________________________________________________