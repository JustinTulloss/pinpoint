dev:
	lein cljsbuild auto &
	http-server

react:
	mkdir -p react/externs
	curl -L http://fb.me/react-0.10.0.min.js > react/react.min.js
	curl -L https://raw.githubusercontent.com/steida/este-library/master/externs/react.js > react/externs/react.js

release: react
	lein cljsbuild once release

distclean: clean
	git clean -xfd

clean:
	rm -rf out release
