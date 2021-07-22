#!groovy

def targetFolders = [
	'Develop': ['Build', 'Deploy'],
	'Stage View': ['Build', 'Deploy']
]

import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import com.cloudbees.hudson.plugins.folder.*

def call() {
	def jenkins = Jenkins.instance
	def String[] allJobs = jenkins.getAllItems(Job.class).fullName	

	targetFolders.each { folders -> 
		def folder = folders.getKey()
		if (jenkins.getItem(folder) != null) {
			println "Working at ${folder}"
		}
	}
}
