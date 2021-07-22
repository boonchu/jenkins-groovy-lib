#!groovy

import jenkins.*
import jenkins.model.*
import hudson.*
import hudson.model.*
import com.cloudbees.hudson.plugins.folder.*

def call() {
    def targetFolders = [
    	'Develop': ['Build', 'Deploy'],
    	'Stage': ['Build', 'Deploy']
    ]

	def jenkins = Jenkins.instance
	def String[] allJobs = jenkins.getAllItems(Job.class).fullName	

	targetFolders.each { folders -> 
		def folder = folders.getKey()
		if (jenkins.getItem(folder) != null) {
			println "Working at ${folder}"
			def custom_jobs = targetFolders[folder]
			println "Working at ${custom_jobs}"
			
			def existingJobs = allJobs.findAll { job -> job.contains(folder) }
			targetJobs.each { job ->
				if (existingJobs.any { existingJob -> existingJob.contains(job) }) {
					println "   Working at ${job.split('/')[0]}"
				}
			}
		}
	}
}
