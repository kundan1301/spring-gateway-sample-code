METHOD - POST, PATH - /post
```
curl -X POST \
  http://localhost:8081/post \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -H 'postman-token: dc5f0b22-7f8a-7c14-6ac2-9a42031eeb96' \
  -d '{
	"host":"google"
}'
```
if host is google, gateway forwards request to google.com else request will be forwarded to httpbin.org

METHOD - GET, PATH - /get
```
curl -X GET \
  http://localhost:8081/get \
  -H 'cache-control: no-cache' \
  -H 'postman-token: 23063650-8c6c-6d69-720f-90c1405a37e1' \
```
50% request will be forwarded to google.com, 50% to httpbin.org

Default:
request forwarded to httpbin.org