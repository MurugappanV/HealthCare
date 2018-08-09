<?php

/*$d="priyadharshini";

list($privKey,$encrypted)=rsa_encrypt($d);

$decrypted=rsa_decrypt($encrypted,$privKey);

echo $encrypted;
echo $decrypted;
*/
function setup()
{
$res = openssl_pkey_new();
//private key export
openssl_pkey_export($res,$privKey);
//public key export
$pubKey = openssl_pkey_get_details($res);
$pubKey = $pubKey["key"];
return array($privKey,$pubKey);
}

function rsa_encrypt($data,$pubKey)
{    

//encrypt data using rsa
openssl_public_encrypt($data, $encrypted, $pubKey);

return $encrypted;

}

function rsa_decrypt($encrypted,$privKey)
{
//decrypt data using rsa
openssl_private_decrypt($encrypted, $decrypted, $privKey);
return $decrypted;

}



?>