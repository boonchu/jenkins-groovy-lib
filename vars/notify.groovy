#!groovy

// https://gist.github.com/jubel-han/12a7f3c86d6e1b2a5124b24ccea60580
// https://stackoverflow.com/questions/58888043/i-am-getting-java-lang-nullpointerexception-on-jenkisn-while-calling-method-from
// https://faun.pub/how-to-get-jenkins-build-job-details-b8c918087030

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

    // https://stackoverflow.com/questions/57364925/jenkins-instance-getitem-fails-for-jobs-in-folders
    def buildName = Jenkins.instance.getItemByFullName('Stage/jenkins-groovy-files')
    if (buildName != null) {

		echo "Last success: ${buildName.getLastSuccessfulBuild()}"
        echo "All builds: ${buildName.getBuilds().collect{ it.getNumber()}}"
        echo "Last build: ${buildName.getLastBuild()}"

		if (buildName.getLastBuild()) {
			last_job_num = buildName.getLastBuild().getNumber()
			def upStreamBuild = Jenkins.getInstance().getItemByFullName('Stage/jenkins-groovy-files').getBuildByNumber(last_job_num)
			println 'LastBuildNumber: ' + last_job_num
            println "LastBuildTime: ${upStreamBuild.getTime().format("YYYY-MMM-dd HH:MM:SS")}"

			if (buildName.getLastSuccessfulBuild()) {
            	println 'LastSuccessNumber: ' + buildName.getLastSuccessfulBuild().getNumber()
            	println 'LastSuccessResult: ' + buildName.getLastSuccessfulBuild().result
			}
		}
    }
}
