cd /Users/nithyamani/Desktop/MyProjects/PercyJavaCLI/src/main/resources
python -m SimpleHTTPServer 8000 &
pid=$!

cd /Users/nithyamani/Desktop/MyProjects/PercyJavaCLI/
export PERCY_TOKEN=c38390c78bc214a208476365644aadce389e7fa07d92af49f19056b6d2095881
build_url=$(npx percy exec -- mvn test -P sample | sed -n '$p')
echo $build_url
build_id=$(echo $build_url | grep -o "builds.*" | cut -d "/" -f 2)
echo $build_id
PERCY_TOKEN=942eb1a4b86235d2c73e9b13166b5e50dea34c1445f7e7ad15e9cf680079c460 npx percy build:wait --build $build_id
kill $pid