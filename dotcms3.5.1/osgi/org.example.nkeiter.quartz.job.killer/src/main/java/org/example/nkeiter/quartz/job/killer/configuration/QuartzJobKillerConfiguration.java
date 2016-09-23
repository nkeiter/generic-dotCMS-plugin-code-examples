package org.example.nkeiter.quartz.job.killer.configuration;

import java.util.ArrayList;

import org.example.nkeiter.quartz.job.killer.beans.QuartzJobBean;

public class QuartzJobKillerConfiguration
{
	// Pre-loaded dotCMS jobs
	public static final String DOTCMS_JOB_GROUP = "dotcms_jobs";
	public static final QuartzJobBean BINARY_CLEANUP_JOB = new QuartzJobBean( "BinaryCleanupJob", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean CLEAN_BLOCK_CACHE_SCHEDULED_TASK = new QuartzJobBean( "CleanBlockCacheScheduledTask", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean DASHBOARD_JOB_IMPL = new QuartzJobBean( "DashboardJobImpl", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean DELETE_OLD_CLICKSTREAMS = new QuartzJobBean( "DeleteOldClickstreams", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean DELIVER_CAMPAIGN_JOB = new QuartzJobBean( "DeliverCampaignJob", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean LINK_CHECKER = new QuartzJobBean( "linkchecker", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean TRASH_CLEANUP_JOB = new QuartzJobBean( "TrashCleanupJob", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean UPDATE_RATING_JOB = new QuartzJobBean( "UpdateRatingJob", DOTCMS_JOB_GROUP );
	public static final QuartzJobBean WEB_DAV_CLEANUP_JOB = new QuartzJobBean( "WebDavCleanupJob", DOTCMS_JOB_GROUP );

	// List of jobs to kill
	public static final ArrayList<QuartzJobBean> JOBS_TO_KILL;
	static
	{
		ArrayList<QuartzJobBean> jobsToKill = new ArrayList<QuartzJobBean>();

		//jobsToKill.add( BINARY_CLEANUP_JOB );
		//jobsToKill.add( CLEAN_BLOCK_CACHE_SCHEDULED_TASK );
		//jobsToKill.add( DASHBOARD_JOB_IMPL );
		jobsToKill.add( DELETE_OLD_CLICKSTREAMS );
		jobsToKill.add( DELIVER_CAMPAIGN_JOB );
		//jobsToKill.add( LINK_CHECKER );
		//jobsToKill.add( TRASH_CLEANUP_JOB );
		//jobsToKill.add( UPDATE_RATING_JOB );
		//jobsToKill.add( WEB_DAV_CLEANUP_JOB );

		JOBS_TO_KILL = jobsToKill;
	}
}
