# Celestia

A new space & tech mod for Minecraft Forge. 

### Downloading

Being as the mod isn't released yet, there are no official downloads at this time. Check back later, maybe? ;-;

### Developing & Contributing

Alright, so you want to contribute to Celestia? Here's how you do it.

Firstly, clone the git repository into any directory. Next, go into the ```/scripts``` directory to find
Celestia's SpeedMouseSetup™ scripts. You'll want to run the ```DevEnvSetup``` script, *running the batchfile if on
Windows or the shell script if you're on macOS or Linux.*

The ```DevEnvSetup``` script is IDE universal, meaning that it caters to both IntelliJ IDEA and Eclipse users.

Once you've run the script:

* If on Eclipse, open the ```/eclipse``` directory as the workspace.
* If on IntelliJ, import the ```build.gradle``` file and wait for it to sync. Once complete, open the Gradle panel and refresh the project.
You may then either run the ```SetupIntelliJRuns``` script, or make your own custom run configurations.