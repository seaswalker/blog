package blog.quartz;

import java.io.IOException;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import blog.util.LucenceUtil;

/**
 * 半夜更新Lucence索引
 * @author skywalker
 *
 */
public class LucenceJob extends QuartzJobBean {

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {
		//重建索引
		try {
			LucenceUtil.rebuild();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
