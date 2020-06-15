# ParallelComputing-TextBigramsAndTrigrams
Compute n-grams on txt file in sequential and parallel version. The process is split into these sections:


1. Compute and estimate occurrences of bigrams and trigrams in two different implementations: sequential and parallel. 

2. Study the different implementations through computational time and speed up, vary depending on number of threads in the parallel version. 

The chosen languages is Java for the sequential version and the Java Thread for the parallel version.

Notebook plots: https://github.com/LorenzoPratesi/ParallelComputing-TextBigramsAndTrigrams/tree/master/notebook

## Some Benchmarks on a 50kb txt file

``` 
Benchmark                                      Mode  Cnt  Score   Error  Units
SequentialComputingBenchmark.sequential2Grams  avgt    5  4,217 ± 0,152  ms/op
SequentialComputingBenchmark.sequential3Grams  avgt    5  4,639 ± 0,141  ms/op


Benchmark                                  (numOfThreads)  Mode  Cnt  Score   Error  Units
ParallelComputingBenchmark.parallel2Grams               1  avgt    5  4,514 ± 0,174  ms/op
ParallelComputingBenchmark.parallel2Grams               2  avgt    5  2,381 ± 0,044  ms/op
ParallelComputingBenchmark.parallel2Grams               3  avgt    5  1,778 ± 0,008  ms/op
ParallelComputingBenchmark.parallel2Grams               4  avgt    5  1,611 ± 0,024  ms/op
ParallelComputingBenchmark.parallel2Grams               5  avgt    5  1,415 ± 0,073  ms/op
ParallelComputingBenchmark.parallel2Grams               6  avgt    5  1,390 ± 0,078  ms/op
ParallelComputingBenchmark.parallel2Grams               7  avgt    5  1,561 ± 0,013  ms/op
ParallelComputingBenchmark.parallel2Grams               8  avgt    5  1,563 ± 0,014  ms/op
ParallelComputingBenchmark.parallel2Grams               9  avgt    5  1,563 ± 0,025  ms/op
ParallelComputingBenchmark.parallel2Grams              10  avgt    5  1,562 ± 0,007  ms/op
ParallelComputingBenchmark.parallel2Grams              11  avgt    5  1,609 ± 0,031  ms/op
ParallelComputingBenchmark.parallel2Grams              12  avgt    5  1,693 ± 0,168  ms/op

ParallelComputingBenchmark.parallel3Grams               1  avgt    5  5,736 ± 0,559  ms/op
ParallelComputingBenchmark.parallel3Grams               2  avgt    5  3,547 ± 0,551  ms/op
ParallelComputingBenchmark.parallel3Grams               3  avgt    5  2,945 ± 0,303  ms/op
ParallelComputingBenchmark.parallel3Grams               4  avgt    5  2,742 ± 0,329  ms/op
ParallelComputingBenchmark.parallel3Grams               5  avgt    5  2,624 ± 0,225  ms/op
ParallelComputingBenchmark.parallel3Grams               6  avgt    5  2,612 ± 0,185  ms/op
ParallelComputingBenchmark.parallel3Grams               7  avgt    5  2,769 ± 0,277  ms/op
ParallelComputingBenchmark.parallel3Grams               8  avgt    5  2,743 ± 0,043  ms/op
ParallelComputingBenchmark.parallel3Grams               9  avgt    5  2,745 ± 0,122  ms/op
ParallelComputingBenchmark.parallel3Grams              10  avgt    5  2,765 ± 0,066  ms/op
ParallelComputingBenchmark.parallel3Grams              11  avgt    5  2,915 ± 0,062  ms/op
ParallelComputingBenchmark.parallel3Grams              12  avgt    5  2,980 ± 0,140  ms/op
```
