Trading Bot
===========
Trading Bot tracks the price of a certain product and will execute a pre-defined trade in
said product when it reaches a given price. After the product has been bought the Trading Bot should keep on tracking the prices and execute a
sell order when a certain price is hit.

Configuration
-------------
For input configuration, the classpath has to contain `config.properties` file with these properties:
* `product.id` - The product id
* `websoket.url` - url to the WebSocket
* `open.position.url` - url to make a trade / open position
* `close.position.url` - url to close position 
* `buy.price` - The buy price
* `up.limit.price` - The upper limit sell price this is the price you are willing to close a position and make a profit.
* `low.limit.price` - The lower limit sell price this the price you want are willing to close a position at and make a loss.
* `delta.equality.price` - The buy price error