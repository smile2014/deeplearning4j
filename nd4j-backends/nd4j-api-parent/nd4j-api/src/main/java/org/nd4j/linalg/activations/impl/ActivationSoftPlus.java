package org.nd4j.linalg.activations.impl;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.apache.commons.math3.util.Pair;
import org.nd4j.linalg.activations.BaseActivationFunction;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.impl.transforms.SoftPlus;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.shade.jackson.annotation.JsonInclude;

/**
 * f(x) = log(1+e^x)
 */
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ActivationSoftPlus extends BaseActivationFunction {

    @Override
    public INDArray getActivation(INDArray in, boolean training) {
        Nd4j.getExecutioner().execAndReturn(new SoftPlus(in));
        return in;
    }

    @Override
    public Pair<INDArray,INDArray> backprop(INDArray in, INDArray epsilon) {
        INDArray dLdz = Nd4j.getExecutioner().execAndReturn(new SoftPlus(in).derivative());
        dLdz.muli(epsilon);
        return new Pair<>(dLdz, null);
    }

    @Override
    public String toString() {
        return "softplus";
    }
}
