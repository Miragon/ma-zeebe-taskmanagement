FROM openresty/openresty:alpine

COPY ./http.lua /usr/local/openresty/lualib/resty/http.lua
COPY ./http_headers.lua /usr/local/openresty/lualib/resty/http_headers.lua
COPY ./http_connect.lua /usr/local/openresty/lualib/resty/http_connect.lua

COPY ./nginx.conf /usr/local/openresty/nginx/conf/nginx.conf

EXPOSE 8080

CMD ["/usr/local/openresty/bin/openresty", "-g", "daemon off;"]