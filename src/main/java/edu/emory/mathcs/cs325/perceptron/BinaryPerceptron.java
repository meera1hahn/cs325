/**
 * Copyright 2015, Emory University
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.emory.mathcs.cs325.perceptron;

import edu.emory.mathcs.cs325.utils.IntDoublePair;


/**
 * @author Jinho D. Choi ({@code jinho.choi@emory.edu})
 */
public class BinaryPerceptron extends AbstractPerceptron
{
	public BinaryPerceptron(double alpha, int featureSize)
	{
		super(alpha);
		weight_vector = new double[featureSize];
	}
	
	public BinaryPerceptron(double alpha, int featureSize, boolean average)
	{
		super(alpha);
		weight_vector = new double[featureSize];
		if (average) average_vector = new double[featureSize];
	}

	@Override
	public int train(int[] x, int y)
	{
		int argmax = decode(x).getInt();
		
		if (y != argmax)
		{
			double delta = alpha * y;
			update(x, delta);
		}
		
		return argmax;
	}
	
	@Override
	public int train(int[] x, int y, int s)
	{
		int argmax = train(x, y);
		
		if (y != argmax)
		{
			double delta = alpha * y * s;
			updateAverage(x, delta);
		}
		
		return argmax;
	}
	
	@Override
	public IntDoublePair decode(int[] x)
	{
		double d = yhat(x);
		return new IntDoublePair(I(d), Math.abs(d));
	}
	
	private double yhat(int[] x)
	{
		double d = 0;
		
		for (int j : x)
			d += weight_vector[j];
		
		return d;
	}
	
	private void update(int[] x, double delta)
	{
		for (int j : x)
			weight_vector[j] += delta;
	}
	
	private void updateAverage(int[] x, double delta)
	{
		for (int j : x)
			average_vector[j] += delta;
	}
}
