package org.example.nkeiter.quartz.job.killer.beans;

public class QuartzJobBean
{
	public static Class<QuartzJobBean> clazz = QuartzJobBean.class;

	private String jobName;
	private String jobGroup;

	public QuartzJobBean( String jobName, String jobGroup )
	{
		this.jobName = jobName;
		this.jobGroup = jobGroup;
	}

	public String getJobName()
	{
		return jobName;
	}

	public String getJobGroup()
	{
		return jobGroup;
	}
}
