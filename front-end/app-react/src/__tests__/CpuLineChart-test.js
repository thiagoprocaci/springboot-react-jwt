'use strict';

import React from 'react';
import {shallow} from 'enzyme';
import {expect, assert} from 'chai';
//import CpuLineChart from '../js/components/chart/CpuLineChart.jsx';



describe('CpuLineChart', () => {

  it('renders correctly', () => {
  	const cpuStatusList = [{  
         "date":"23/11/2016 17:16:17",
         "ipRunnerMachine":"0.0.0.0",
         "nonNiceUserCpuTicks":1398408,
         "niceUserCpuTicks":33559,
         "systemCpuTicks":592740,
         "idleCpuTicks":77439362,
         "ioWaitCpuTicks":284217,
         "irqCpuTicks":0,
         "softirqCpuTicks":3409,
         "stolenCpuTicks":0
      }]

  //  const wrapper = shallow(<CpuLineChart cpuStatusList={cpuStatusList} perspective='cpu' />); 	
    //console.log(wrapper.debug())
   // expect(wrapper.find('ChartTitle')).to.have.html('<div style="width:100%;margin-top:10px;margin-left:20px;"><h4>CPU Ticks</h4></div>') 
   // expect(wrapper.find('XAxis').prop('height')).to.equal(30)
   // expect(wrapper.find('XAxis').prop('width')).to.equal(0)
   // expect(wrapper.find('CartesianGrid')).to.exist;
   // expect(wrapper.find('Bar')).to.have.length(8);
  });


  it('renders nothing', () => {
  	//const cpuStatusList = []

   // const wrapper = shallow(<CpuLineChart cpuStatusList={cpuStatusList} perspective='cpu' />); 	    
   // expect(wrapper.find('div')).to.have.length(1);
   // expect(wrapper.find('div')).to.have.html('<div></div>')     
  });
});