export PERCY_TOKEN=1ebbbadee4037b650fe12fd6ba0bcdd6e0c5db40c3670dc5c40757a176356574
build_url=$(npx percy exec -- mvn test -P sample | sed -n '$p')
echo $build_url
build_id=$(echo $build_url | grep -o "builds.*" | cut -d "/" -f 2)
echo $build_id
PERCY_TOKEN=942eb1a4b86235d2c73e9b13166b5e50dea34c1445f7e7ad15e9cf680079c460 npx percy build:wait --build $build_id