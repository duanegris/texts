

<?php
// Temporary upload image name
$image1 = 'wired.jpg';
$image2 = 'world_mesh.jpg';
$size1=GetImageSize( $image1 );
$size2=GetImageSize( $image2 );

// Maximum image width

// Resize the image and save        
$rsrc_img1 = ImageCreateFromJPEG( $image1 );
$rsrc_img2 = ImageCreateFromJPEG( $image2 );
$final_size = array( $size1[0]+$size2[0], $size1[1]+$size2[1] );
$cadre = ImageCreateTrueColor( $final_size[0], $final_size[1] );


//bool imagecopymerge  ( resource $dst_im  , resource $src_im  , int $dst_x  , int $dst_y  , int $src_x  , int $src_y  , int $src_w  , int $src_h  , int $pct  )
imagecopymerge($cadre, $rsrc_img1, 0        ,0        , 0, 0, $size1[0],$size1[1], 100);   

imagecopymerge($cadre, $rsrc_img2, $size1[0],$size1[1], 0, 0, $size2[0],$size2[1], 100);   
ImageJPEG( $cadre, 'output.jpg' );

ImageDestroy( $cadre );
?>
