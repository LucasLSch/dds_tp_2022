package domainTests;

import ddsutn.domain.organization.Member;
import ddsutn.domain.organization.Sector;
import org.junit.jupiter.api.Test;

public class MemberTest extends TestDataFill {

  @Test
  public void memberCanApplyToSector() {

    Member someMember = this.memberSvc.findById(1L);
    Sector someSector = this.sectorSvc;
    someMember.applyToSector(someSector);

    this.
  }
}
