"""Contains definitions for the preactivation form of Residual Networks.
Residual networks (ResNets) were originally proposed in:
[1] Kaiming He, Xiangyu Zhang, Shaoqing Ren, Jian Sun
    Deep Residual Learning for Image Recognition. arXiv:1512.03385
The full preactivation 'v2' ResNet variant implemented in this module was
introduced by:
[2] Kaiming He, Xiangyu Zhang, Shaoqing Ren, Jian Sun
    Identity Mappings in Deep Residual Networks. arXiv: 1603.05027
The key difference of the full preactivation 'v2' variant compared to the
'v1' variant in [1] is the use of batch normalization before every weight layer.
Typical use:
   from tensorflow.contrib.slim.nets import resnet_v2
ResNet-101 for image classification into 1000 classes:
   # inputs has shape [batch, 224, 224, 3]
   with slim.arg_scope(resnet_v2.resnet_arg_scope()):
      net, end_points = resnet_v2.resnet_v2_101(inputs, 1000, is_training=False)
ResNet-101 for semantic segmentation into 21 classes:
   # inputs has shape [batch, 513, 513, 3]
   with slim.arg_scope(resnet_v2.resnet_arg_scope()):
      net, end_points = resnet_v2.resnet_v2_101(inputs,
                                                21,
                                                is_training=False,
                                                global_pool=False,
                                                output_stride=16)
"""
from __future__ import absolute_import
from __future__ import division
from __future__ import print_function

import collections
import tensorflow as tf

slim = tf.contrib.slim

# 这个 nametuple 名字是Block，它有3个键，分别为 'scope', 'unit_fn', 'args'；可以更简单的把它理解为字典；
#     scope: The scope of the `Block`.
#     unit_fn: The ResNet unit function which takes as input a `Tensor` and
#       returns another `Tensor` with the output of the ResNet unit.
#     args: A list of length equal to the number of units in the `Block`. The list
#       contains one (depth, depth_bottleneck, stride) tuple for each unit in the
#       block to serve as argument to unit_fn.

class Block(collections.namedtuple('Block', ['scope', 'unit_fn', 'args'])):
    # 下采样
    # Subsamples the input along the spatial dimensions.
    """
    Args:
    inputs: A `Tensor` of size [batch, height_in, width_in, channels].
    factor: The subsampling factor.
    scope: Optional variable_scope.
  Returns:
    output: A `Tensor` of size [batch, height_out, width_out, channels] with the
      input, either intact (if factor == 1) or subsampled (if factor > 1).
    """
    def subsample(inputs, factor, scope=None):
        if factor == 1:
            return inputs
        else:
            return slim.max_pool2d(inputs, [1,1], stride=factor, scope = scope)

    """
        Strided 2-D convolution with 'SAME' padding.
    """
    def conv2d_same(inputs, num_outputs, kernel_size, stride, rate=1, scope=None):
        if stride ==1 :
            return slim.conv2d(inputs, num_outputs, kernel_size, stride=1, rate=rate, padding='SAME',scope=scope)
        else:
            kernel_size_effective = kernel_size + (kernel_size - 1) * (rate - 1)
            pad_total = kernel_size_effective - 1
            pad_beg = pad_total // 2
            pad_end = pad_total - pad_beg
            inputs = tf.pad(inputs,
                            [[0,0], [pad_beg,pad_end], [pad_beg,pad_end], [0,0]])
            return slim.conv2d(inputs, num_outputs, kernel_size, stride=stride, rate=rate, padding='VALID',scope=scope)

