JSXC=node_modules/react-tools/bin/jsx

react-0.10.0.zip:
	curl http://facebook.github.io/react/downloads/react-0.10.0.zip > $@

react-0.10.0: react-0.10.0.zip
	unzip react-0.10.0.zip

dev: react-0.10.0
	$(JSXC) --watch src/ build/ &
	http-server

distclean:
	git clean -xfd

.INTERMEDIATE: react-0.10.0.zip
.PHONY: clean distclean dev
