package org.example.nkeiter.quartz.job.killer.osgi;

import com.dotmarketing.osgi.GenericBundleActivator;
import com.dotmarketing.util.Logger;

import java.util.Date;

import org.example.nkeiter.quartz.job.killer.beans.QuartzJobBean;
import org.example.nkeiter.quartz.job.killer.configuration.QuartzJobKillerConfiguration;

import org.osgi.framework.BundleContext;

import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class Activator extends GenericBundleActivator
{
	private static final String PLUGIN_NAME = "Quartz Job Killer";

	public void killQuartzJobs()
	{
		try
		{
			Logger.info( this, "Got to killQuartzJobs() " + PLUGIN_NAME );

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();

			for ( QuartzJobBean jobToKill : QuartzJobKillerConfiguration.JOBS_TO_KILL )
			{
				if ( jobToKill != null )
				{
					Logger.info( this, "Killing Quartz job... [jobName] : " + jobToKill.getJobName() + " [groupName] : " + jobToKill.getJobGroup() );
					scheduler.deleteJob( jobToKill.getJobName(), jobToKill.getJobGroup() );
				}
			}

		}
		catch( Exception exception )
		{
			Logger.error( this, "Activator.killQuartzJobs()", exception );
		}

		runJobScheduleReport();
	}

	public void runJobScheduleReport()
	{
		try
		{
			Logger.info( this, "Got to runJobScheduleReport() " + PLUGIN_NAME );

			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			String jobScheduleReport = "";

			// Loop through all job group names
			for ( String groupName : scheduler.getJobGroupNames() )
			{
				// Loop through all job names in the group
				for ( String jobName : scheduler.getJobNames( groupName ) )
				{
					// Get the job's triggers, find next fire time
					Trigger[] triggers = scheduler.getTriggersOfJob( jobName, groupName );
					Date nextFireTime = triggers[ 0 ].getNextFireTime();

					jobScheduleReport += "\n" + "[jobName] : " + jobName + " [groupName] : " + groupName + " [nextFireTime] : " + nextFireTime;
				}
			}

			Logger.info( this, jobScheduleReport );
		}
		catch( Exception exception )
		{
			Logger.error( this, "Activator.runJobScheduleReport() Unable to access quartz job schedule information.", exception );
		}
	}

	@Override
	public void start( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to start( BundleContext ) " + PLUGIN_NAME );

		// Initializing services...
		initializeServices( bundleContext );

		runJobScheduleReport();

		killQuartzJobs();
	}

	@Override
	public void stop( BundleContext bundleContext ) throws Exception
	{
		Logger.info( this, "Got to stop( BundleContext ) " + PLUGIN_NAME );

		// Unpublish bundle services
		unpublishBundleServices();
	}
}
