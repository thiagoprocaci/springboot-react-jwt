'use strict';

import React from 'react';
import {render} from 'enzyme';
import {expect} from 'chai';
import ChartTitle from '../js/components/chart/ChartTitle.jsx';



describe('ChartTitle', () => {

  it('renders correctly', () => {
    const wrapper = render(<ChartTitle label="title"  />);
 	expect(wrapper).to.have.text('title');
 	expect(wrapper.find('div')).to.have.html('<div style="width:100%;margin-top:10px;margin-left:20px;"><h4>title</h4></div>')
  });
});