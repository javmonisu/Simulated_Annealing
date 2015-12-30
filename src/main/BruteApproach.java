package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
/**
 * Clase que la seleccion que permite que todos los nodos sean cubiertos por el minimo numero de estaciones de incendio.
 * @author javier
 *
 */
public class BruteApproach {
	static //Mapa que contiene los resultados para cada ejecuci�n con el n� de estaciones conectadas
	HashMap<Solution, Integer> resultadosFinales = new HashMap<>();
			
	static //Arraylist con todas las estaciones
	ArrayList<int[]> listaEstaciones = new ArrayList<>();
	
	public static void main(String[] args) {
		addStations();
		bruteApproach();
	}
	/**
	 * Metodo que a�ade las conexiones entre los nodos.
	 */
	public static void addStations(){
		//Declaracion de las conexiones de cada estacion
		int[] estacion1 = {2,4,5,1};
		listaEstaciones.add(estacion1);
		
		int[] estacion2 = {1,5,6,3,2};
		listaEstaciones.add(estacion2);
		
		int[] estacion3 = {2,6,7,3};
		listaEstaciones.add(estacion3);
		
		int[] estacion4 = {1,4,5,8,10,11};
		listaEstaciones.add(estacion4);
		
		int[] estacion5 = {1,2,4,5,6,8};
		listaEstaciones.add(estacion5);
		
		int[] estacion6 = {2,5,6,8,3,9,7};
		listaEstaciones.add(estacion6);
		
		int[] estacion7 = {3,6,7,9,13};
		listaEstaciones.add(estacion7);
		
		int[] estacion8 = {4,5,6,8,9,11,12};
		listaEstaciones.add(estacion8);
		
		int[] estacion9 = {6,8,7,9,12,13};
		listaEstaciones.add(estacion9);
		
		int[] estacion10 = {4,10,11,14};
		listaEstaciones.add(estacion10);
		
		int[] estacion11 = {4,8,10,11,12,14,15};
		listaEstaciones.add(estacion11);
		
		int[] estacion12 = {8,9,11,12,13,14,15,16};
		listaEstaciones.add(estacion12);
		
		int[] estacion13 = {7,9,12,13,15,16};
		listaEstaciones.add(estacion13);
		
		int[] estacion14 = {10,11,14,15};
		listaEstaciones.add(estacion14);

		int[] estacion15 = {11,12,13,14,15,16};
		listaEstaciones.add(estacion15);
		
		int[] estacion16 = {13,15,16};
		listaEstaciones.add(estacion16);
	}
	
	/**
	 * Metodo que genera una posible solucion, la cual es una lista de estaciones.
	 * @param numeroEstaciones
	 * @return
	 */
	public static ArrayList<Integer> generateSolution(int numeroEstaciones){
		int[] estaciones = new Random().ints(0,15).distinct().limit(numeroEstaciones).toArray();
		ArrayList<Integer> posibleSolucion = new ArrayList<>();

	    for (int index = 0; index < estaciones.length; index++)
	    {
	    	posibleSolucion.add(estaciones[index]);
	    }
	    
		return posibleSolucion; 
	}
	
	/**
	 * Opcion bruta de alcanzar el maximo
	 */
	public static void bruteApproach(){
		ArrayList<Integer> posibleSolucion = null;
		HashSet<Integer> nodosCubiertos = null;
		for(int i = 0 ; i < 1000000; i++){
			nodosCubiertos = new HashSet<>();

			//Numero de estaciones con las que cubriremos el problema
			int numeroDeEstacion = new Random().nextInt(15) + 1;
			
			posibleSolucion = generateSolution(numeroDeEstacion);
			
			//Para cada nodo cogemos sus conexiones
			for (int j = 0 ; j < posibleSolucion.size(); j++)
			{
				for(int num : listaEstaciones.get(posibleSolucion.get(j))){
					nodosCubiertos.add(num);
				}
			}
			Collections.sort(posibleSolucion);
			//Posible solucion contiene las estaciones.
			resultadosFinales.put(new Solution(numeroDeEstacion, nodosCubiertos.toString(),posibleSolucion), nodosCubiertos.size());
		}
		Map.Entry<Solution, Integer> maxEntry = null;

		for (Map.Entry<Solution, Integer> entry : resultadosFinales.entrySet())
		{
			//Miramos si se consigue alcanzar el m�ximo global.
			if( entry.getValue() == 16){
				System.out.println(entry.getKey().getTamano());
//				System.out.println("---------\nOPTIMA: Tama�o de solucion:" + entry.getKey().getTamano() + "\nNodos con estacion: "+entry.getKey().getNodosConEstacion() + "\nNodos cubiertos: "+entry.getKey().getNodosCubiertos());
				if (maxEntry == null || entry.getKey().getTamano() < maxEntry.getKey().getTamano())
			    {
			        maxEntry = entry;
			    }
			}else{
//				System.out.println("---------\nINVALIDA: Tama�o de solucion:" + entry.getKey().getTamano() + "\nNodos con estacion: "+entry.getKey().getNodosConEstacion() + "\nNodos cubiertos: "+entry.getKey().getNodosCubiertos());
			}
		}
		System.out.println("Tama�o de solucion: " + maxEntry.getKey().getTamano() + "\nNodos con estacion: "+maxEntry.getKey().getNodosConEstacion() + "\nNodos cubiertos: "+maxEntry.getKey().getNodosCubiertos());
	}
}