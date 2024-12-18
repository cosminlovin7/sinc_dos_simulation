import redis
import time

r = redis.Redis(host='localhost', port=6379, db=0)

# r.json().set('mykey', '.', {
#     'name': 'Nicole',
#     'age': 24,
#     'single': True,
#     'skills': []
# })


nicoleObj = r.json().get('mykey', '.')

print(nicoleObj['age'] + 123)


r.json().set('mykey', '.age', nicoleObj['age'] + 123)

print(r.json().get('mykey', '.'))

timestamp = int(time.time())  # Current timestamp in seconds
print(timestamp)