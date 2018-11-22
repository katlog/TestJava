package headfirst.facade.hometheater;

public class HomeTheaterTestDrive {
	public static void main(String[] args) {
		Amplifier amp = new Amplifier("Top-O-LineMain Amplifier");
		Tuner tuner = new Tuner("Top-O-LineMain AM/FM Tuner", amp);
		DvdPlayer dvd = new DvdPlayer("Top-O-LineMain DVD Player", amp);
		CdPlayer cd = new CdPlayer("Top-O-LineMain CD Player", amp);
		Projector projector = new Projector("Top-O-LineMain Projector", dvd);
		TheaterLights lights = new TheaterLights("Theater Ceiling Lights");
		Screen screen = new Screen("Theater Screen");
		PopcornPopper popper = new PopcornPopper("Popcorn Popper");
 
		HomeTheaterFacade homeTheater = 
				new HomeTheaterFacade(amp, tuner, dvd, cd, 
						projector, screen, lights, popper);
 
		homeTheater.watchMovie("Raiders of the Lost Ark");
		homeTheater.endMovie();
	}
}
