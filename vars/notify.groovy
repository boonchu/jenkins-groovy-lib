#!groovy

// https://stackoverflow.com/questions/58888043/i-am-getting-java-lang-nullpointerexception-on-jenkisn-while-calling-method-from

import com.cloudbees.hudson.plugins.folder.Folder
import jenkins.model.Jenkins
import java.text.SimpleDateFormat
import groovy.time.TimeCategory


def call(def result) {
	notify_build(result)
}


def notify_build(def buildStatus) {
    buildStatus = buildStatus ?: 'STARTED'
    echo "LOG->INFO : Build status is ${buildStatus}"

    def buildName = Jenkins.instance.getItemByFullName('Stage/jenkins-groovy-files')

    if (buildName != null) {
		echo "Last success: ${buildName.getLastSuccessfulBuild()}"
        echo "All builds: ${buildName.getBuilds().collect{ it.getNumber()}}"
        echo "Last build: ${buildName.getLastBuild()}"
        echo "Is building: ${job.isBuilding()}"
    }
}
