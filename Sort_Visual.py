import matplotlib.pyplot as plt
import numpy as np
from IPython.display import display, clear_output
import time
import random

# Function definitions
def is_sorted(nums, curr_index):
    return not (curr_index > 0 and nums[curr_index] < nums[curr_index - 1])

def generate_random_index(length, used_indices, curr_index):
    if curr_index == 0:
        used_indices.clear()
    rand_index = random.randint(0, length - 1)
    while rand_index in used_indices:
        rand_index = random.randint(0, length - 1)
    used_indices.append(rand_index)
    return rand_index

def bogus_sort(nums):
    nums_sorted = nums.copy()
    used_indices = []
    curr_index = 0

    while curr_index < len(nums):
        nums_sorted[curr_index] = nums[generate_random_index(len(nums), used_indices, curr_index)]
        curr_index = curr_index + 1 if is_sorted(nums_sorted, curr_index) else 0

        yield nums_sorted.copy()  # Yield a copy of the current state for animation

# Initial setup
arr = np.random.randint(1, 50, size=10)  # Generate initial random array

# Create figure and axis
fig, ax = plt.subplots()
ax.set_title('Bogus Sort Visualization')
ax.set_xlabel('Index')
ax.set_ylabel('Value')

# Bar plot initialization
bars = ax.bar(range(len(arr)), arr, align='edge')
plt.show()

# Perform Bogus Sort and update plot
sorting_generator = bogus_sort(arr.copy())
for sorted_arr in sorting_generator:
    for bar, val in zip(bars, sorted_arr):
        bar.set_height(val)

    # Update plot
    clear_output(wait=True)
    display(fig)
    time.sleep(0.1)

# Final sorted plot
clear_output(wait=True)
plt.bar(range(len(sorted_arr)), sorted_arr, align='edge')
plt.title('Bogus Sort Visualization (Sorted)')
plt.xlabel('Index')
plt.ylabel('Value')
plt.show()