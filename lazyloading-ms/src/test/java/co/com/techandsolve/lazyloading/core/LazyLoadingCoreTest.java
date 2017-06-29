package co.com.techandsolve.lazyloading.core;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LazyLoadingCoreTest {

	@InjectMocks
	private LazyLoadingCore core;
	
	@Test
	public void calcular(){
		List<Integer> lineasNumericas = Arrays.asList(
				5,4,30,30,1,1,3,20,20,20,11,1,2,3,4,5,6,
				7,8,9,10,11,6,9,19,29,39,49,59,10,32,56,
				76,8,44,60,47,85,71,91
				);
		List<Integer> respuestas = core.iniciarCalculo(lineasNumericas);
		Assert.assertTrue(respuestas.size() == 5);
		Assert.assertTrue(respuestas.get(0) == 2);
		Assert.assertTrue(respuestas.get(1) == 1);
		Assert.assertTrue(respuestas.get(2) == 2);
		Assert.assertTrue(respuestas.get(3) == 3);
		Assert.assertTrue(respuestas.get(4) == 8);
	}
	
}
