###################
#                 #
#  Docker Deploy  #
#                 #
###################

Build:
docker build -t rmadridbasket-admin/alpine-server:1.0 .

Run:
docker run --rm -it rmadridbasket-admin/alpine-server:1.0 /bin/sh
