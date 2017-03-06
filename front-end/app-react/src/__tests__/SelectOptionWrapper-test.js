'use strict';

import React from 'react';
import {shallow} from 'enzyme';
import {expect} from 'chai';
import SelectOptionWrapper from '../js/components/wrapper/SelectOptionWrapper.jsx';



describe('SelectOptionWrapper', () => {

  it('renders correctly', () => {
    const wrapper = shallow(<SelectOptionWrapper label="title" key="b" value="val" />);
 	expect(wrapper).to.have.text('title');
 	expect(wrapper.find('option')).to.have.value('val')
 	expect(wrapper.find('option')).to.have.html('<option value="val">title</option>') 	
  });
});