import time

start_time = time.perf_counter()

for _ in range(100_000):
    pass

end_time = time.perf_counter()

execution_time = end_time - start_time

print(f"Время выполнения: {execution_time*1000:.4f} ms")
