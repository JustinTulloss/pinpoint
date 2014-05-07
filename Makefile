dev:
	lein cljsbuild auto &
	http-server

clean:
	rm -rf out
