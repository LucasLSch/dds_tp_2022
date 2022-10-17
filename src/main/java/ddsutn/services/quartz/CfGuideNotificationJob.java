package ddsutn.services.quartz;

import ddsutn.repositories.OrganizationRepo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

public class CfGuideNotificationJob implements Job {

  private String guideLink = "ToBeDefined";

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    OrganizationRepo
            .getInstance()
            .getAll()
            .forEach(organization -> {
              organization.notifyAllMembers(this.getFinalMessage());
              organization.notify(this.getFinalMessage());
            });
  }

  private String getFinalMessage() {
    return
            "Click the link to access the carbon footprint recommendations guide!: " + this.guideLink;
  }

}
