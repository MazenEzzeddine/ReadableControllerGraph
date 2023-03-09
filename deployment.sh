#!/bin/bash

cd strimzi/
cd examples/
cd metrics
kubectl create -f kafka-metrics.yaml

