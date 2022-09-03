package nz.ac.wgtn.srm.event;

public class Domestic extends Competition {

	private Season latestSeason;
	private String name;
	
	public Domestic(String name) {
		
	}
	
	public void updateSeason(Season newSeason) {
		if (this.latestSeason != null) {
			this.latestSeason.setNext(newSeason);
			newSeason.setPrevious(this.latestSeason);
		}
		this.latestSeason = newSeason;
		return;
	}

}
