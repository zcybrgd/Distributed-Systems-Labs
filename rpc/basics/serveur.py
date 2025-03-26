from xmlrpc.server import SimpleXMLRPCServer

def add(x, y):
    return x + y

server = SimpleXMLRPCServer(("0.0.0.0", 8000))
server.register_function(add, "add")

print("Serveur RPC en attente...")
server.serve_forever()
