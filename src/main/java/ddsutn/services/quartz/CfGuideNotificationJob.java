package ddsutn.services.quartz;

import ddsutn.repositories.OrganizationRepo;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

public class CfGuideNotificationJob implements Job {

  private final String guideLink = "ToBeDefined";

  @Autowired
  private OrganizationRepo organizationRepo;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    organizationRepo
        .findAll()
        .forEach(organization -> {
          organization.notifyAllMembers(this.getFinalMessage());
          organization.notify(this.getFinalMessage());
        });
  }

  private String getFinalMessage() {
    return "Click the link to access the carbon "
        + "footprint recommendations guide!: "
        + this.guideLink;
  }

}
