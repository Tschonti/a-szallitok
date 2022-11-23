
import * as React from 'react';
import Map, { Marker, Popup } from 'react-map-gl';
import 'mapbox-gl/dist/mapbox-gl.css'; 
import { Page } from '../../components/Page';
import axios from 'axios';
import { Delivery, Location } from '../../types/Delivery';
import { getErrorMessage } from '../../util/errorMessage';
import { useState } from 'react';
import { Text, useToast } from '@chakra-ui/react';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../../auth/AuthContext';
import { clearStorage } from 'mapbox-gl';


export const MapPage = () => {
    const toast = useToast()
    const [deliveries, setDeliveries] = useState<Delivery[]>([])
    const [storedDelivery, setStoredDelivery] = useState<Delivery | null>(null)
    const [isSorceLocation, setSourceLocation] = useState(true)
    const { user } = React.useContext(AuthContext)
    const navigate = useNavigate()

    if (!user) {
        navigate('/')
    }

    React.useEffect(() => {
        const fetchData = async () => {
            try {
                const res = await axios.get<Delivery[]>('/allDeliveries')
                if (res.status === 200) {
                    setDeliveries(res.data)
                }
            } catch(error) {
                toast({
                    title: 'Error fetching deliveries',
                    description: getErrorMessage(error),
                    status: 'error',
                    duration: 5000
                })
            }
        }
        fetchData()
    }, [toast])
    return (
        <Page>
        <Map
        initialViewState={{
            latitude: 47.46,
            longitude: 19.0,
            zoom: 10
        }}
        style={{width: '75vw', height: '80vh'}}
        mapStyle="mapbox://styles/mapbox/streets-v9"
        mapboxAccessToken={'pk.eyJ1IjoidHNjaG9udGkiLCJhIjoiY2xhcXZwNWF2MTkyeTNubnBiMWg2aWV2YSJ9.EdcDpQOSd_TH-_BaKpVe-A'}
        >
            {deliveries.length > 0 && deliveries.map(d => (
                <div key={d._id}>
                <Marker onClick={() => {setStoredDelivery(d); setSourceLocation(true)}} longitude={d.sourceLocation.coordinate.longitude} latitude={d.sourceLocation.coordinate.latitude} color="red" />
                <Marker onClick={() => {setStoredDelivery(d); setSourceLocation(false)}} longitude={d.destinationLocation.coordinate.longitude} latitude={d.destinationLocation.coordinate.latitude} color="blue" />
                </div>
            ))}
        { storedDelivery !== null && ( 
            <Popup closeOnClick={false} 
            anchor="top"
                latitude={isSorceLocation ? storedDelivery.sourceLocation.coordinate.latitude : storedDelivery.destinationLocation.coordinate.latitude} 
                longitude={isSorceLocation ? storedDelivery.sourceLocation.coordinate.longitude : storedDelivery.destinationLocation.coordinate.longitude} 
                onClose={() => setStoredDelivery(null)}  >
                <Text>{storedDelivery.title}</Text>
            </Popup>
        )}
        </Map>
        </Page>
    );
}