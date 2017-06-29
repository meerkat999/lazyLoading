package co.com.techandsolve.lazyloading.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LazyLoadingCore {
	
	private List<Integer> pesos;
	
	private Integer tamanoPesos, ans;
	
	public List<Integer> iniciarCalculo(List<Integer> lineasNumericas) {
		List<Integer> respuestas = new ArrayList<>();
		if(lineasNumericas != null && lineasNumericas.size() > 0){
			Integer p = 0;
			Integer t = lineasNumericas.get(p);
			for (int i = 0; i < t; i++) {
				p++;
				limpiarGlobales();
				Integer n = lineasNumericas.get(p);
				Integer limitCase = (p + 1) + n;
				for (int j = p + 1; j < limitCase; j++) {
					p++;
					Integer w = lineasNumericas.get(j);
					meterEnElCamion(w);
				}
				prepareArray();
				tamanoPesos = pesos.size();
				calcularViaje(0, tamanoPesos -1 , 0, 0);
				respuestas.add(ans);
			}
		}
		return respuestas;
	}

	private void limpiarGlobales() {
		ans = 0;
		pesos = new ArrayList<>();
	}
	
	private void meterEnElCamion(Integer w){
		if(w >= 50){
			ans++;
		}else{
			pesos.add(w);
		}
	}

	private boolean calcularViaje(Integer s, Integer i, Integer p, Integer c){
		if(c >= 50){
			ans++;
			return true;
		}
		if(s >= tamanoPesos || i < 0){
			return false;
		}
		p = pesos.get(s);
		if(calcularViaje(s, i-1, p, c+p)){
			return calcularViaje(s+1, i-1, 0, 0);
		}
		return false;
	}
	
	private void prepareArray() {
		Collections.sort(this.pesos);
		Collections.reverse(this.pesos);
	}

}
