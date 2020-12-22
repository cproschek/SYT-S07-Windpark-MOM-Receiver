package windpark;

public class WindengineSimulation {
	
	private double getRandomDouble( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		double rounded = Math.round(number * 100.0) / 100.0; 
		return rounded;
		
	}

	private int getRandomInt( int inMinimum, int inMaximum ) {

		double number = ( Math.random() * ( (inMaximum-inMinimum) + 1 )) + inMinimum; 
		Long rounded = Math.round(number); 
		return rounded.intValue();

	}
	
	public WindengineData getData( String inWindengineID ) {
		
		WindengineData data = new WindengineData();
		data.setWindengineID( inWindengineID );
		data.setWindspeed( getRandomDouble( 0, 80 ) );
		data.setTemperature( getRandomDouble( -40, 40 ) );
		data.setPower( getRandomDouble( 0, 2000 ) );
		data.setBlindpower( getRandomDouble( 0, 200 ) );
		data.setRotationspeed( getRandomDouble( 0, 200 ) );
		data.setBladeposition( getRandomInt( 0, 45 ) );
		return data;
		
	}

}
