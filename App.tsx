import React, {useState} from 'react';
import {SafeAreaView, Text, Button, NativeModules} from 'react-native';

type BarcodeScannerType = {
  openScanner: (callback: (result: string) => void) => void;
};

const {BarcodeScanner} = NativeModules as {BarcodeScanner: BarcodeScannerType};

export default function App() {
  const [result, setResult] = useState('No scan yet');

  const openScanner = () => {
    if (BarcodeScanner && BarcodeScanner.openScanner) {
      BarcodeScanner.openScanner((scanResult: string) => {
        setResult(scanResult);
      });
    } else {
      setResult('BarcodeScanner module not linked');
    }
  };

  return (
    <SafeAreaView style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <Button title="Open Barcode Scanner" onPress={openScanner} />
      <Text style={{marginTop: 20, fontSize: 18}}>{result}</Text>
    </SafeAreaView>
  );
}
