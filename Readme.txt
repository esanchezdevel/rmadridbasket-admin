###################
#                 #
#  Docker Deploy  #
#                 #
###################

Build:
docker build -t rmadridbasket-admin/alpine-server:1.0 .

Run:
docker run -it --name rmadridbasket-admin --network rmadridbasket -p 127.0.0.1:8080:8080 rmadridbasket-admin/alpine-server:1.0 /bin/sh
