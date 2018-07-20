const http = require('http');
const fs = require("fs");

const APP_ENV_PORT = process.env.PORT || 80;

const APP_ENV_VARS = {
	API_HOST: process.env.API_HOST || 'http://localhost:8202'
}

console.log('APP_ENV_PORT: ', APP_ENV_PORT);
console.log('APP_ENV_VARS: ', APP_ENV_VARS);

http.createServer(function(request, response) {
	let { url } = request;
	url = url.toLowerCase();

	switch( url ){
		case '/':
		case '/index.html':
			response.writeHead(200, {'Content-Type': 'text/html'});
			response.write(fs.readFileSync('webapp/index.html', 'utf-8'));
			response.end();
			break;
		case '/index.css':
			response.writeHead(200, {'Content-Type': 'text/css'});
			response.write(fs.readFileSync('webapp/index.css', 'utf-8'));
			response.end();
			break;
		case '/index.js':
			response.writeHead(200, {'Content-Type': 'application/javascript'});
			response.write(fs.readFileSync('webapp/index.js', 'utf-8'));
			response.end();
			break;

		case '/env':
			response.writeHead(200, {'Content-Type': 'application/json'});
			response.write(JSON.stringify({
				API_HOST: process.env.API_HOST || 'http://localhost:8202'
			}));
			response.end();
			break;

		default:
			response.write("404 - Not found");
			response.end();
			break;
	}
}).listen(APP_ENV_PORT);
