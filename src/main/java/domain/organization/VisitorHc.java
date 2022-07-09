package domain.organization;

public class VisitorHc {
	public Double calculateHC(Organization org){
		Double total = 0.0;
		total += this.calculateHCActivities(org);
		total += org.sectorList.stream().mapToDouble(sector -> this.calculateHCSector(sector,org)).sum();
		return total;
	}
 public Double calculateHCSector(Sector sector, Organization org){
		return sector.getMembers().stream().mapToDouble(member -> calculateHCMember(member)).sum();
 }

 public Double calculateHCMember(Member member){
		//actual calculation of HC by members
		return 0.0;
 }
	public Double calculateHCActivities(Organization org){
		//esta branch no tienen activities todavia
	}

}
