package domain.organization;


import domain.journey.Journey;
import domain.measurements.ActivityData;

import java.util.List;
import java.util.stream.Collectors;

public class VisitorCF {

	public Double calculateCFOrg(Organization org){
		Double total = 0.0;
		total += this.calculateCFActivities(org.getDataActivities());
		total += org.getSectorList()
						.stream()
						.mapToDouble(sector -> this.calculateCFSector(sector))
						.sum();
		return total;
	}
 	public Double calculateCFSector(Sector sector){
		return sector.getMembers().stream().mapToDouble(member -> calculateCFMember(member)).sum();
 	}

 	public Double calculateCFMember(Member member){
		List<Journey> journeys = member.getJourneyList();
		List<ActivityData> dataActivities = journeys.stream()
				.flatMap(journey -> journey
						.getDataActivities()
						.stream())
				.collect(Collectors.toList());
		return this.calculateCFActivities(dataActivities);
	}

 	public Double calculateCFActivities(List<ActivityData> dataActivities){
		return dataActivities
					.stream()
					.mapToDouble(dataActivity -> dataActivity.calcularHc())
					.sum();
	}

}
