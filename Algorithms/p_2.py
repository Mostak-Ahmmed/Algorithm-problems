   
import time
 
count_seconds = 4
for i in reversed(range(count_seconds + 1)):
    if i > 0:
        print(i, end='>>>')
        time.sleep(0.5)
    else:
        print('Start')