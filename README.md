# Server List Ping Version Handler

When a client pings your proxy, your proxy needs to respond with which version is supported. With this plugin you are able to configure which protocol versions should have a successful response like this:
upload_2021-6-10_3-30-57.png
and which protocol versions should receive a customizable message like this:
upload_2021-6-10_3-30-48.png


# Server list websites
This plugin also solves an issue which some motd plugins cause.

When server list websites such as planetminecraft ping your server they set their protocol version to the maximum singed 16-bit int value: 32767 In order to properly be categorized with your server version you should return the proper protocol version when planetminecraft pings, instead of returning the sender's protocol version. This plugin handles that so that your server is properly categorized on those server list websites with the protocol version that you specify in the configuration.

# Config

Code (YAML):
# for reference to protocol numbers see https://wiki.vg/Protocol_version_numbers
mimimumprotocol: 754
maximumprotocol: 755
# this is the protocol version that is sent to server list websites
recommendedprotocol: 755
supportedversions: '1.16.x, 1.17.x'

# Spigot release
https://www.spigotmc.org/resources/serverlistping-protocol-handler.93148/
