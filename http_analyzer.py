from scapy.all import *
import hashlib
import redis

port = 8080
seen_packets = set()

def packet_handler(packet):
    #TODO: do not forget to treat IPv6 as well...
    if packet.haslayer(TCP) and packet.haslayer(IP):
        if packet[TCP].dport == 8080:
            if packet.haslayer(Raw):
                payload = packet[Raw].load
                try:
                    # Check if the packet contains HTTP headers
                    if b"HTTP" in payload:
                        packet_hash = hashlib.sha256(payload).hexdigest()
                        if packet_hash not in seen_packets:
                            seen_packets.add(packet_hash)
                            print(f"HTTP data: {payload}")
                except Exception as e:
                    print(f"Error processing packet: {e}")

sniff(iface="lo", filter="tcp port 8080", prn=packet_handler)