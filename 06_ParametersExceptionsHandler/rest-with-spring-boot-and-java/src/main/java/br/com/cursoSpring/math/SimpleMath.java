package br.com.cursoSpring.math;

public class SimpleMath {
	
	public Double sum(Double numberOne, Double numberTwo){
		return numberOne + numberTwo;
	}

	public Double subtract(Double numberOne, Double numberTwo) {
		return numberOne - numberTwo;
	}
	
	public Double multiply(Double numberOne, Double numberTwo) {
		return numberOne * numberTwo;
	}
	
	public Double divide(Double numberOne, Double numberTwo) {
		return numberOne / numberTwo;
	}
	
	public Double average(Double numberOne, Double numberTwo) {
		return (numberOne + numberTwo) / 2;
	}
	
	public Double sqrt(Double number) {
		return Math.sqrt(number);
	}

}
