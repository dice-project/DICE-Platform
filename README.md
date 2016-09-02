# DICE-Platform

This is the DICE Platform repository. It includes all the necessary plugins and features to build the DICE IDE.

DICE IDE is based on Eclipse Neon 4.6.0 version. So if you want to build your own DICE IDE, you need Java 1.8 version at least.

The DICE IDE is built using Tycho building technology, that uses maven to perform it (https://eclipse.org/tycho/).

If you want to build your own DICE IDE, you need to follow these steps:

* Download Eclipse 4.6.0, concretely the SDK distribution for your OS: http://download.eclipse.org/eclipse/downloads/drops4/R-4.6-201606061100/
* Start Eclipse, create a new workspace and import all the plugins:
* You need to install GIT and MAVEN plugins
	* GIT
		* Click on Help -> Install new software, and include this repository: http://download.eclipse.org/egit/updates
		* Select only the "Eclipse Git Team Provider" feature. The other features are not necessary.
		* Click on next button, accept the licence, and finish the installation.
	* MAVEN
		* Click on Help -> Install new software, and include this repository: http://download.eclipse.org/technology/m2e/releases/
		* Select only the "m2e - Maven Integration for Eclipse (includes Incubating components)" feature. The other features are not necessary.
		* Click on next button, accept the licence, and finish the installation.
	* Restart Eclipse when both installations finish.
* Once Eclipse is restarted, you need to modify some preferences for GIT. Go to Window -> Preferences, and browse to Team -> Git.
	* Check the repository folder to point to the correct folder
	* Modify the .gitconfig file from the Configuration tab as follows:
```
[user]
	email = your email
	name = Your name
[branch]
	autosetuprebase = always
[color]
	ui = auto
[push]
	default = simple
[core]
	excludesfile = .gitignore
```
* Now you need to import the projects to the Workspace
	* Open the "Git Repositories" view via Window -> Show View -> Others -> Git -> Git Repositories
	* Copy the URL of this GitHub project, and paste it to this Eclipse view. Automatically a wizard is opened and ask you about some needed information:
		* The URL, host and repository path (automatically set)
		* User and password
		* The branches you want to import
		* The destination folder (automatically set to your home GIT folder)
	* Once done, right click to this repository and click on "Import Projects" action. Another wizard is opened. Just follow the steps and import all the projects.
		* Verifh that option "Search for nested projects" is checked in the projects list page.
* Now you need to select the DICE Target Platform. It is placed on the 'org.dice.target' project (org.dice.target.target file):
	* Open this file "org.dice.target/org.dice.target.target"
	* Click "Set as Target Platform" button at the top right corner
	* Wait until all the dependencies are downloaded. This step could take a lot of minutes, so be patience
* Next step is to check if all the plugin dependencies are correctly loaded:
	* Go to 'org.dice.product' plugin
	* Open 'dice.product' file
	* Click on Overview Tab
	* Click the button "Validate" at the top right corner and check that there are no dependency problems
* Now you can launch the DICE IDE in order to check that all works fine
	* Click the button "Launch an Eclipse application" and wait until it is loaded
	* If any error occurred, it appears on the Console view (Window --> Show View --> Console). Open it before launching the application
* Final step is to build your RCP. It is really easy to do with Tycho and maven. In the "org.dice.root" project there are two launchers that allows to perform a build and a clean (clean is only necessary if you want to delete previous build files).
	* Click on the menu Run -> Run configurations -> Maven Build -> DICE Builder. Then click on run button and wait.
	* If no problems were found on the building process, you will have your files in "org.dice.product" plugin, at "org.dice.product/target/products" folder.

# New version of the DICE IDE

* Before building a new version of the RCP, you need to set up the new version number. You can do it modifying the pom.xml file from the "org.dice.configuration" project. Replace the <dice.version> property with the new version.
* Also you need to change the features and plugins version number from the plugin.xml file, and the dependencies of the features too. You will know that all is well configured because when a feature points to a plugin with a failing version number, the feature is marked with a Warning. Also, the build of the product will fail too.

# How Tycho works

You can check how Tycho works by having a look at the pom.xml files. The main thing to have in consideration is the folder structure. All the projects and plugins are placed into a recommended folder structure at "org.dice.root" project. You can find this folders inside:
* bundles: contains all the plugins of the IDE
* features: contains all the features of the IDE
* releng: contains some necessary projects for the building process
	* org.dice.configuration: contains the root pom.xml
	* org.dice.product: contains the product files of the IDE
	* org.dice.target: contains the target platform file (not necessary the project, but we need the target file)
	* org.dice.update: contains the update site of the IDE (not necessary)

If you want to know more about Tycho, just visit its main website on Eclipse, or I recommend you to have a look at Vogella howto to know how to use Tycho to build your products :)
* http://www.vogella.com/tutorials/EclipseTycho/article.html
