package domain.organization;

import domain.journey.Journey;
import domain.measurements.ActivityData;

import java.util.List;
import java.util.stream.Collectors;

public class VisitorHc {

	public Double calculateHCOrg(Organization org){
		Double total = 0.0;
		total += this.calculateHCActivities(org.getDataActivities());
		total += org.getSectorList()
						.stream()
						.mapToDouble(sector -> this.calculateHCSector(sector))
						.sum();
		return total;
	}
 	public Double calculateHCSector(Sector sector){
		return sector.getMembers().stream().mapToDouble(member -> calculateHCMember(member)).sum();
 	}

 	public Double calculateHCMember(Member member){
		List<Journey> journeys = member.getJourneyList();
		List<ActivityData> dataActivities = journeys.stream()
				.flatMap(journey -> journey
						.getDataActivities()
						.stream())
				.collect(Collectors.toList());
		return this.calculateHCActivities(dataActivities);
	}

 	public Double calculateHCActivities(List<ActivityData> dataActivities){
		return dataActivities
					.stream()
					.mapToDouble(dataActivity -> dataActivity.calcularHc())
					.sum();
	}

}
