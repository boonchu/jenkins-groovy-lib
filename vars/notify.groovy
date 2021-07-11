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

    def colorMap = [ 'STARTED': '#F0FFFF', 'SUCCESS': '#008B00', 'FAILURE': '#FF0000' ]
    def subject = "Pipeline: ${env.JOB_DISPLAY_NAME} - #${env.BUILD_NUMBER} ${buildStatus}"
    def summary = "${subject} (${env.BUILD_URL})"
    def colorName = colorMap[buildStatus]

    def jenkinsDir = Jenkins.instance.getItemByFullName('Stage View')
    echo "LOG-->INFO-->Directory to Check: ${jenkinsDir.getFullName()}"
    def folderObj = getFolders(jenkinsDir)
    folderObj.each{ folder ->
		def jobObj = getJobs(folder)
        def totalBuilds = []
       	jobObj.each{job -> 
			def builds = []
			builds = processJob(job)
			total = [totatBuilds, builds].flatten().findAll{it} 
		}
	}
    
    println "Total Builds: ${totatBuilds}"
    println "Total Builds Count: ${buildCount = totatBuilds.size()}"

    if ("${buildStatus}" != "${env.PREVIOUS_BUILD_RESULT}") {
        echo "message: ${summary}, color: ${colorName}"
    }
}


def processJob(Item job) {
	def buildnum = []
	if (job.getLastBuild() != null) {
		job.builds.each {
			if (it.getTime().compareTo(earliestDate) == 1 && it.getTime().compareTo(latestDate) == -1 ){
				buildnum.add(it.displayName[1..-1])
			}
		}
		return buildnum
	}
}


def getJobs(Item folder){
    def jobs = []
    folder.getItems().each{
        if(it instanceof com.cloudbees.hudson.plugins.folder.AbstractFolder){
            getJobs(it)
        }else{
            jobs.add(it)
        }
    }
  	return jobs
}


def getFolders(Item directory){
    def folders = []
    directory.getItems().each{
        if(it instanceof com.cloudbees.hudson.plugins.folder.Folder){
            folders.add(it)
        }
    }
    return folders
}
